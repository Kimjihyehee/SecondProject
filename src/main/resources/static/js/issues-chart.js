
    const userStateCount = /* 위에서 구한 데이터 */;
    const categories = Object.keys(userStateCount);
    const seriesData = Object.keys(userStateCount[Object.keys(userStateCount)[0]]).map(user => {
    return {
    name: user,
    data: categories.map(state => userStateCount[state][user] || 0)
}
});

    Highcharts.chart('chart-container', {
    chart: {
    type: 'column'
},
    title: {
    text: 'User Issue Count by State'
},
    xAxis: {
    categories: categories
},
    yAxis: {
    title: {
    text: 'Count'
}
},
    series: seriesData
});
