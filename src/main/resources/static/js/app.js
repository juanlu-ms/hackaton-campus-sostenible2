// Variables globales
let contenedoresData = [];
let estadoChart = null;

// Inicializar al cargar la página
$(document).ready(function () {
    console.log('Iniciando Campus Sostenible...');
    inicializarDashboard();
});

// Función principal de inicialización
function inicializarDashboard() {
    cargarDatos();
    crearGrafico();
}

// function cargarDatos() {
//     // Remove the fetch for now
//     contenedoresData = [
//         {id: 1, ubicacion: 'Biblioteca', tipo: 'Papel', nivel: 95},
//         {id: 2, ubicacion: 'Cafetería', tipo: 'Orgánico', nivel: 87},
//         {id: 3, ubicacion: 'Aula A', tipo: 'Plástico', nivel: 72},
//         {id: 4, ubicacion: 'Gimnasio', tipo: 'General', nivel: 45},
//         {id: 5, ubicacion: 'Parking', tipo: 'Vidrio', nivel: 23}
//     ];
//     actualizarDashboard();
// }

// Cargar datos (simulados para desarrollo)
// function cargarDatos() {
//     fetch('/api/contenedores')
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('Error al obtener los datos');
//             }
//             return response.json();
//         })
//         .then(data => {
//             contenedoresData = [
//                 {id: 1, ubicacion: 'Biblioteca', tipo: 'Papel', nivel: 95},
//                 {id: 2, ubicacion: 'Cafetería', tipo: 'Orgánico', nivel: 87},
//                 {id: 3, ubicacion: 'Aula A', tipo: 'Plástico', nivel: 72},
//                 {id: 4, ubicacion: 'Gimnasio', tipo: 'General', nivel: 45},
//                 {id: 5, ubicacion: 'Parking', tipo: 'Vidrio', nivel: 23}
//             ];
//             // contenedoresData = data; // Asigna los datos reales
//             actualizarDashboard();  // Actualiza el dashboard con los datos reales
//         })
//         .catch(error => {
//             console.error('Error:', error);
//         });
// }

function cargarDatos() {
    fetch('/api/contenedores')
        .then(response => response.json())
        .then(data => {
            contenedoresData = data;
            actualizarDashboard();
        })
        .catch(error => {
            console.error('Error:', error);
            // Fallback a datos simulados
            contenedoresData = [
                {id: 1, ubicacion: 'Biblioteca', tipo: 'Papel', nivel: 95, ultimo_vaciado: '2023-10-01'},
                {id: 2, ubicacion: 'Cafetería', tipo: 'Orgánico', nivel: 87, ultimo_vaciado: '2023-10-01'},
                {id: 3, ubicacion: 'Aula A', tipo: 'Plástico', nivel: 72, ultimo_vaciado: '2023-10-01'},
                {id: 4, ubicacion: 'Gimnasio', tipo: 'General', nivel: 45, ultimo_vaciado: '2023-10-01'},
                {id: 5, ubicacion: 'Parking', tipo: 'Vidrio', nivel: 23, ultimo_vaciado: '2023-10-01'}
            ];;
            actualizarDashboard();
        });
}

// Crear gráfico de dona
function crearGrafico() {
    const ctx = document.getElementById('estadoChart').getContext('2d');
    estadoChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Crítico', 'Medio', 'Bajo'],
            datasets: [{
                data: [33, 33, 33],
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

// // src/main/resources/static/js/app.js
// document.addEventListener("DOMContentLoaded", function () {
//     const ctx = document.getElementById("estadoChart").getContext("2d");
//     estadoChart = new Chart(ctx, {
//         type: "doughnut", // Tipo de gráfico (puede ser 'line', 'pie', 'bar', etc.)
//         data: {
//             labels: ["Llenos", "Medio", "Vacíos"],
//             datasets: [{
//                 label: "Estado de Contenedores",
//                 data: [10, 20, 30], // Datos de ejemplo
//                 backgroundColor: ["#dc3545", "#ffc107", "#28a745"]
//             }]
//         },
//         options: {
//             responsive: false,
//             maintainAspectRatio: false,
//             plugins: {
//                 legend: {
//                     position: "top"
//                 }
//             }
//         }
//     });
// });


// Actualizar dashboard completo
function actualizarDashboard() {
    actualizarMetricas();
    actualizarTabla();
    actualizarGrafico();
}

// Actualizar métricas principales
function actualizarMetricas() {
    const total = contenedoresData.length;
    const llenos = contenedoresData.filter(c => c.nivel >= 90).length;
    const medios = contenedoresData.filter(c => c.nivel >= 50 && c.nivel < 90).length;

    $('#total-contenedores').text(total);
    $('#contenedores-llenos').text(llenos);
    $('#contenedores-medio').text(medios);
    // document.getElementById('total-contenedores').textContent = total;
    // document.getElementById('contenedores-llenos').textContent = llenos;
    // document.getElementById('contenedores-medio').textContent = medios;
}

// Actualizar datos del gráfico
function actualizarGrafico() {
    if (estadoChart) {
        const criticos = contenedoresData.filter(c => c.nivel >= 90).length;
        const medios = contenedoresData.filter(c => c.nivel >= 50 && c.nivel < 90).length;
        const bajos = contenedoresData.filter(c => c.nivel < 50).length;

        estadoChart.data.datasets[0].data = [criticos, medios, bajos];
        estadoChart.update();
    }
}

// Actualizar tabla de contenedores críticos
function actualizarTabla() {
    const tbody = $('#tabla-contenedores');
    tbody.empty();

    // Solo mostrar contenedores con nivel > 70%
    const criticos = contenedoresData
        .filter(c => c.nivel > 70)
        .sort((a, b) => b.nivel - a.nivel);

    criticos.forEach(contenedor => {
        const estadoClass = getEstadoClass(contenedor.nivel);
        const estadoBadge = getEstadoBadge(contenedor.nivel);

        const fila = `
            <tr class="${estadoClass}">
                <td>${contenedor.ubicacion}</td>
                <td>${contenedor.tipo}</td>
                <td>
                    <div class="progress">
                        <div class="progress-bar bg-${getColorProgress(contenedor.nivel)}" 
                             style="width: ${contenedor.nivel}%"></div>
                    </div>
                    <small>${contenedor.nivel}%</small>
                </td>
                <td><span class="badge ${estadoBadge}">${getEstadoTexto(contenedor.nivel)}</span></td>
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