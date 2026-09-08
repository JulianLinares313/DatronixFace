// js/proveedores.js - CRUD completo (sin auto-inicialización)

import { getData, postData, putData, deleteData } from './api.js';

let proveedoresData = [];
let proveedorEditando = null;

console.log('✅ proveedores.js cargado');

// ============ INICIALIZACIÓN ============
function inicializarProveedores() {
    console.log('✅ inicializarProveedores ejecutado');
    cargarProveedores();
    configurarEventosProveedores();
    const usuario = JSON.parse(localStorage.getItem('usuario') || '{}');
    const nombreModulo = document.getElementById('nombreUsuarioModulo');
    if (nombreModulo) nombreModulo.textContent = usuario.nombreUsuario || 'Usuario';
}

// ============ CARGAR PROVEEDORES ============
async function cargarProveedores() {
    console.log('✅ cargarProveedores ejecutado');
    try {
        proveedoresData = await getData('/proveedores');
        console.log('✅ Datos recibidos:', proveedoresData);
        renderizarTabla(proveedoresData);
    } catch (error) {
        console.error('❌ Error al cargar proveedores:', error);
        document.getElementById('tbodyProveedores').innerHTML =
            `<tr><td colspan="6" class="text-center" style="color:red;">Error: ${error.message}</td></tr>`;
    }
}

// ============ RENDERIZAR TABLA ============
function renderizarTabla(data) {
    const tbody = document.getElementById('tbodyProveedores');
    if (!tbody) {
        console.error('❌ No se encontró el elemento tbodyProveedores');
        return;
    }
    if (!data || data.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6" class="text-center">No hay proveedores registrados</td></tr>`;
        return;
    }
    tbody.innerHTML = data.map(p => `
        <tr data-id="${p.idProveedor}">
            <td>${p.idProveedor}</td>
            <td>${p.nombreEmpresa}</td>
            <td>${p.contactoProveedor || '-'}</td>
            <td>${p.telefonoProveedor}</td>
            <td>${p.emailProveedor || '-'}</td>
            <td>${p.direccionProveedor || '-'}</td>
        </tr>
    `).join('');
}

// ============ BÚSQUEDA ============
function buscarProveedores() {
    const termino = document.getElementById('txtBuscarProveedor').value.trim().toLowerCase();
    if (!termino) {
        renderizarTabla(proveedoresData);
        return;
    }
    const filtrados = proveedoresData.filter(p =>
        p.idProveedor.toString().includes(termino) ||
        p.nombreEmpresa.toLowerCase().includes(termino) ||
        (p.contactoProveedor && p.contactoProveedor.toLowerCase().includes(termino))
    );
    renderizarTabla(filtrados);
}

// ============ MODALES ============
function abrirModalAgregar() {
    document.getElementById('modalAgregarProveedor').classList.add('active');
    limpiarFormularioAgregar();
    document.getElementById('txtIdProveedor').focus();
}

function cerrarModalAgregar() {
    document.getElementById('modalAgregarProveedor').classList.remove('active');
}

function abrirModalEditar() {
    const selectedRow = document.querySelector('#tablaProveedores tbody tr.selected');
    if (!selectedRow) {
        alert('Selecciona un proveedor de la tabla');
        return;
    }
    const id = parseInt(selectedRow.dataset.id);
    const proveedor = proveedoresData.find(p => p.idProveedor === id);
    if (!proveedor) {
        alert('Proveedor no encontrado');
        return;
    }
    proveedorEditando = proveedor;
    document.getElementById('txtIdProveedorEdit').value = proveedor.idProveedor;
    document.getElementById('txtNombreEmpresaEdit').value = proveedor.nombreEmpresa;
    document.getElementById('txtContactoProveedorEdit').value = proveedor.contactoProveedor || '';
    document.getElementById('txtTelefonoProveedorEdit').value = proveedor.telefonoProveedor;
    document.getElementById('txtEmailProveedorEdit').value = proveedor.emailProveedor || '';
    document.getElementById('txtDireccionProveedorEdit').value = proveedor.direccionProveedor || '';
    document.getElementById('modalEditarProveedor').classList.add('active');
}

function cerrarModalEditar() {
    document.getElementById('modalEditarProveedor').classList.remove('active');
    proveedorEditando = null;
}

// ============ CRUD ============
async function guardarProveedor() {
    const id = parseInt(document.getElementById('txtIdProveedor').value.trim());
    const empresa = document.getElementById('txtNombreEmpresa').value.trim();
    const contacto = document.getElementById('txtContactoProveedor').value.trim();
    const telefono = parseInt(document.getElementById('txtTelefonoProveedor').value);
    const email = document.getElementById('txtEmailProveedor').value.trim();
    const direccion = document.getElementById('txtDireccionProveedor').value.trim();

    if (!id || !empresa || isNaN(telefono)) {
        alert('ID, Empresa y Teléfono son obligatorios.');
        return;
    }

    const nuevoProveedor = {
        idProveedor: id,
        nombreEmpresa: empresa,
        contactoProveedor: contacto || null,
        telefonoProveedor: telefono,
        emailProveedor: email || null,
        direccionProveedor: direccion || null
    };

    try {
        const creado = await postData('/proveedores', nuevoProveedor);
        proveedoresData.push(creado);
        renderizarTabla(proveedoresData);
        cerrarModalAgregar();
        alert('✅ Proveedor guardado exitosamente');
    } catch (error) {
        alert('❌ Error al guardar: ' + error.message);
    }
}

async function guardarProveedorEdit() {
    const id = parseInt(document.getElementById('txtIdProveedorEdit').value);
    const empresa = document.getElementById('txtNombreEmpresaEdit').value.trim();
    const contacto = document.getElementById('txtContactoProveedorEdit').value.trim();
    const telefono = parseInt(document.getElementById('txtTelefonoProveedorEdit').value);
    const email = document.getElementById('txtEmailProveedorEdit').value.trim();
    const direccion = document.getElementById('txtDireccionProveedorEdit').value.trim();

    if (!empresa || isNaN(telefono)) {
        alert('Empresa y Teléfono son obligatorios.');
        return;
    }

    const proveedorActualizado = {
        idProveedor: id,
        nombreEmpresa: empresa,
        contactoProveedor: contacto || null,
        telefonoProveedor: telefono,
        emailProveedor: email || null,
        direccionProveedor: direccion || null
    };

    try {
        await putData(`/proveedores/${id}`, proveedorActualizado);
        const index = proveedoresData.findIndex(p => p.idProveedor === id);
        if (index !== -1) {
            proveedoresData[index] = proveedorActualizado;
        }
        renderizarTabla(proveedoresData);
        cerrarModalEditar();
        alert('✅ Proveedor actualizado');
    } catch (error) {
        alert('❌ Error al actualizar: ' + error.message);
    }
}

async function eliminarProveedor() {
    const selectedRow = document.querySelector('#tablaProveedores tbody tr.selected');
    if (!selectedRow) {
        alert('Selecciona un proveedor para eliminar');
        return;
    }
    const id = parseInt(selectedRow.dataset.id);
    if (!confirm(`¿Eliminar el proveedor con ID ${id}?`)) return;

    try {
        await deleteData(`/proveedores/${id}`);
        proveedoresData = proveedoresData.filter(p => p.idProveedor !== id);
        renderizarTabla(proveedoresData);
        alert('🗑 Proveedor eliminado');
    } catch (error) {
        alert('❌ Error al eliminar: ' + error.message);
    }
}

// ============ LIMPIAR FORMULARIOS ============
function limpiarFormularioAgregar() {
    document.getElementById('txtIdProveedor').value = '';
    document.getElementById('txtNombreEmpresa').value = '';
    document.getElementById('txtContactoProveedor').value = '';
    document.getElementById('txtTelefonoProveedor').value = '';
    document.getElementById('txtEmailProveedor').value = '';
    document.getElementById('txtDireccionProveedor').value = '';
}

function limpiarFormularioEditar() {
    document.getElementById('txtIdProveedorEdit').value = '';
    document.getElementById('txtNombreEmpresaEdit').value = '';
    document.getElementById('txtContactoProveedorEdit').value = '';
    document.getElementById('txtTelefonoProveedorEdit').value = '';
    document.getElementById('txtEmailProveedorEdit').value = '';
    document.getElementById('txtDireccionProveedorEdit').value = '';
}

// ============ CONFIGURAR EVENTOS ============
function configurarEventosProveedores() {
    console.log('✅ configurarEventosProveedores ejecutado');
    const btnBuscar = document.getElementById('btnBuscarProveedor');
    if (btnBuscar) {
        btnBuscar.addEventListener('click', buscarProveedores);
        console.log('✅ Evento buscar asignado');
    } else {
        console.error('❌ btnBuscarProveedor no encontrado');
    }

    const txtBuscar = document.getElementById('txtBuscarProveedor');
    if (txtBuscar) {
        txtBuscar.addEventListener('keyup', (e) => {
            if (e.key === 'Enter') buscarProveedores();
        });
        console.log('✅ Evento keyup asignado');
    } else {
        console.error('❌ txtBuscarProveedor no encontrado');
    }

    const btnActualizar = document.getElementById('btnActualizarProveedor');
    if (btnActualizar) {
        btnActualizar.addEventListener('click', cargarProveedores);
        console.log('✅ Evento actualizar asignado');
    } else {
        console.error('❌ btnActualizarProveedor no encontrado');
    }

    const btnAgregar = document.getElementById('btnAgregarProveedor');
    if (btnAgregar) {
        btnAgregar.addEventListener('click', abrirModalAgregar);
        console.log('✅ Evento agregar asignado');
    } else {
        console.error('❌ btnAgregarProveedor no encontrado');
    }

    document.getElementById('closeAgregarProveedor')?.addEventListener('click', cerrarModalAgregar);
    document.getElementById('btnVolverProveedor')?.addEventListener('click', cerrarModalAgregar);
    document.getElementById('btnGuardarProveedor')?.addEventListener('click', guardarProveedor);
    document.getElementById('btnLimpiarProveedor')?.addEventListener('click', limpiarFormularioAgregar);

    document.getElementById('btnModificarProveedor')?.addEventListener('click', abrirModalEditar);
    document.getElementById('closeEditarProveedor')?.addEventListener('click', cerrarModalEditar);
    document.getElementById('btnVolverProveedorEdit')?.addEventListener('click', cerrarModalEditar);
    document.getElementById('btnGuardarProveedorEdit')?.addEventListener('click', guardarProveedorEdit);
    document.getElementById('btnLimpiarProveedorEdit')?.addEventListener('click', limpiarFormularioEditar);

    document.getElementById('btnEliminarProveedor')?.addEventListener('click', eliminarProveedor);

    document.addEventListener('click', function (e) {
        const tr = e.target.closest('#tablaProveedores tbody tr');
        if (tr) {
            document.querySelectorAll('#tablaProveedores tbody tr').forEach(row => row.classList.remove('selected'));
            tr.classList.add('selected');
        }
    });
}

// ============ EXPONER FUNCIÓN DE INICIALIZACIÓN ============
window.inicializarProveedores = inicializarProveedores;