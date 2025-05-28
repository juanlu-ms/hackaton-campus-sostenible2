// Variables globales
let contenedoresData = [];
let estadoChart = null;

// Inicializar al cargar la página
$(document).ready(function () {
    if ($('#estadoChart').length > 0) {
        inicializarDashboard();
    }

    if ($('#tabla-historial-contenedores').length > 0) {
        cargarDatos(); // Esto ya llama a mostrarHistorialContenedores() internamente si aplica
    }
});

// Función principal de inicialización
function inicializarDashboard() {
    cargarDatos();
    crearGrafico();
}

function cargarDatos() {
    fetch('/api/contenedores')
        .then(response => response.json())
        .then(data => {
            contenedoresData = data;
            actualizarDashboard();

            if ($('#tabla-historial-contenedores').length > 0) {
                mostrarHistorialContenedores();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            actualizarDashboard();
        });
}


// Crear gráfico de dona
function crearGrafico() {
    const canvas = document.getElementById('estadoChart');
    if (!canvas) {
        console.warn('No se encontró el canvas #estadoChart. Saltando gráfico.');
        return;
    }

    const ctx = canvas.getContext('2d');
    estadoChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Crítico', 'Medio', 'Bajo'],
            datasets: [{
                data: [0, 0, 0],
                backgroundColor: ['#dc3545', '#ffc107', '#28a745']
            }]
        },
        options: {
            responsive: false,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            }
        }
    });
}

// Actualizar dashboard completo
function actualizarDashboard() {
    actualizarMetricas();
    actualizarTabla();
    actualizarGrafico();
}

// Actualizar métricas principales
function actualizarMetricas() {
    const total = contenedoresData.length;
    const llenos = contenedoresData.filter(c => c.levelPercent >= 75).length;
    const medios = contenedoresData.filter(c => c.levelPercent >= 50 && c.levelPercent < 75).length;

    $('#total-contenedores').text(total);
    $('#contenedores-llenos').text(llenos);
    $('#contenedores-medio').text(medios);
    $('#contenedores-bajo').text(medios);
}

// Actualizar datos del gráfico
function actualizarGrafico() {
    if (estadoChart) {
        const criticos = contenedoresData.filter(c => c.levelPercent >= 75).length;
        const medios = contenedoresData.filter(c => c.levelPercent >= 50 && c.levelPercent < 75).length;
        const bajos = contenedoresData.filter(c => c.levelPercent < 50).length;

        estadoChart.data.datasets[0].data = [criticos, medios, bajos];
        estadoChart.update();
    }
}

function actualizarTabla() {
    const tbody = $('#tabla-contenedores');
    tbody.empty();

    // Solo mostrar contenedores con nivel > 70%
    const criticos = contenedoresData
        .sort((a, b) => b.levelPercent - a.levelPercent);

    criticos.forEach(contenedor => {
        const estadoClass = getEstadoClass(contenedor.levelPercent);
        const estadoBadge = getEstadoBadge(contenedor.levelPercent);

        const fila = `
            <tr class="${estadoClass}">
                <td>${contenedor.center + " - " + contenedor.location}</td>
                <td>${contenedor.type}</td>
                <td>${contenedor.capacity}</td>
                <td>
                    <div class="progress">
                        <div class="progress-bar bg-${getColorProgress(contenedor.levelPercent)}" 
                             style="width: ${contenedor.levelPercent}%"></div>
                    </div>
                    <small>${contenedor.levelPercent}%</small>
                </td>
                <td>
                    <span class="badge ${estadoBadge} text-dark">${getEstadoTexto(contenedor.levelPercent)}</span>
                </span>
                </td>
            </tr>
        `;
        tbody.append(fila);
    });
}

// Funciones auxiliares
function getEstadoClass(nivel) {
    if (nivel >= 90) return 'fila-critica';
    if (nivel >= 50) return 'fila-media';
    return 'fila-baja';
}

function getEstadoBadge(nivel) {
    if (nivel >= 90) return 'badge-critico';
    if (nivel >= 50) return 'badge-medio';
    return 'badge-bajo';
}

function getEstadoTexto(nivel) {
    if (nivel >= 90) return 'Crítico';
    if (nivel >= 50) return 'Medio';
    return 'Bajo';
}

function getColorProgress(nivel) {
    if (nivel >= 90) return 'danger';
    if (nivel >= 50) return 'warning';
    return 'success';
}

// Función para actualizar datos (llamar desde backend)
function actualizarDatos() {
    console.log('Actualizando datos...');
    // Aquí harías fetch al backend
    // fetch('/api/contenedores')...
    cargarDatos(); // Por ahora recarga los datos simulados
}

function mostrarHistorialContenedores() {
    const tbody = $('#tabla-historial-contenedores tbody');
    tbody.empty();

    contenedoresData.forEach(contenedor => {
        if (!contenedor.historial || contenedor.historial.length === 0) return;

        contenedor.historial.forEach(h => {
            const fecha = new Date(h.timestamp);
            const fila = `
                <tr>
                    <td>${contenedor.id}</td>
                    <td>${contenedor.center} - ${contenedor.location}</td>
                    <td>${contenedor.type}</td>
                    <td>${contenedor.capacity}</td>
                    <td>${h.level}%</td>
                    <td>${fecha.toLocaleDateString()}</td>
                    <td>${fecha.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</td>
                </tr>
            `;
            tbody.append(fila);
        });
    });
}
