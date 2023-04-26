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
  props: {
    colors: {
      type: Array,
      required: true,
    },
  },

  data() {
    return {
      chartOptions: {
        chart: {
          id: 'vuechart-example',
        },

        colors: this.colors,

        plotOptions: {
          bar: {
            distributed: true, // this line is mandatory
          },
        },
        xaxis: {
          categories: ['Vuejs', 'Angular', 'React', 'Bootstrap'],
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
                  horizontal: true,
                },
              },
            },
          },
        ],
      },
      series: [
        {
          name: 'series-1',
          data: [30, 40, 35, 50],
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
