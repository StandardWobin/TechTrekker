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
      props: {
    colors: {
      type: Array,
      required: true,
    },},
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
       colors: this.colors,

        plotOptions: {
          splin: {
            distributed: true, // this line is mandatory
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
          name: 'Vue',
          data: [31, 40, 28, 51, 42, 109, 100],
        },
        {
          name: 'Angular',
          data: [11, 32, 45, 32, 34, 52, 4],
        },
               {
          name: 'React',
          data: [11, 90, 5, 32, 11, 5, 2],
        },
               {
          name: 'Bootstrap',
          data: [11, 32, 13, 9, 34, 88, 18],
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
