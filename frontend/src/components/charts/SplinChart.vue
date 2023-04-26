<template>
  <div>
    <apexchart
      width="700"
      height="200"
      type="area"
      style="position: relative; left: 4vw"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'SplinChart',
  data() {
    return {
      chartOptions: {
        chart: {
          height: 350,
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: 'smooth',
        },
        xaxis: {
          type: 'datetime',
          categories: [
            '2018-09-19T00:00:00.000Z',
            '2018-09-19T01:30:00.000Z',
            '2018-09-19T02:30:00.000Z',
            '2018-09-19T03:30:00.000Z',
            '2018-09-19T04:30:00.000Z',
            '2018-09-19T05:30:00.000Z',
            '2018-09-19T06:30:00.000Z',
          ],
        },
        tooltip: {
          x: {
            format: 'dd/MM/yy HH:mm',
          },
        },

        responsive: [
          {
            breakpoint: 1000,
            options: {
              chart: {
                width: 260,
              },
              plotOptions: {
                bar: {
                  horizontal: true,
                },
              },
            },
          },
        ],
      },
      series: [
        {
          name: 'series1',
          data: [31, 40, 28, 51, 42, 109, 100],
        },
        {
          name: 'series2',
          data: [11, 32, 45, 32, 34, 52, 41],
        },
      ],
    };
  },
});
</script>

<style>
.centered {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
