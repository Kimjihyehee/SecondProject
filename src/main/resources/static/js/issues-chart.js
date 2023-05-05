document.addEventListener('DOMContentLoaded', function() {
    // 추출한 데이터 -main.html에 미리 선언
    // var dates = /*[[${dates}]]*/[];
    // var statuses = /*[[${statuses}]]*/[];

    console.log("dates : "+ dates);
    console.log("statuses : "+ statuses);

    // Highcharts 그래프 생성
    Highcharts.chart('chart-container', {
        title: {
            text: 'GitHub Issues Graph'
        },
        xAxis: {
            categories: dates
        },
        yAxis: {
            title: {
                text: 'Status'
            },
            categories: statuses
        },
        series: [{
            name: 'Issues',
            data: statuses
        }]
    });
});