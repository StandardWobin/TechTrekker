<template>
  <div>
    <apexchart
      height="200"
      type="bar"


      style="position: relative; left: 4vw; max-width: 760px"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'BarChart',
  data() {
    return {
      chartOptions: {
        chart: {
          id: 'vuechart-example',
        },
        xaxis: {
          categories: ['Vuejs', 'Angular', 1993, 1994, 1995, 1996, 1997, 1998],
        },
        responsive: [
          {
            breakpoint: 1000,
            options: {
              chart: {
                  width: 250,
              },
              plotOptions: {
                bar: {
                  horizontal: true,                },
              },
            },
          },
        ],
      },
      series: [
        {
          name: 'series-1',
          data: [30, 40, 35, 50, 49, 60, 70, 91],
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
