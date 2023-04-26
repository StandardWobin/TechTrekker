<template>
  <div>
    <apexchart
      width="700"
      height="200"
      type="pie"
      style="position: relative; left: 4vw"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PieChart',
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

        labels: ['Vue', 'Angular', 'React', 'Bootstrap'],
        plotOptions: {
          pie: {
            distributed: true, // this line is mandatory
          },
        },
        colors: this.colors,

        responsive: [
          {
            breakpoint: 1000,
            options: {
              chart: {
                width: 300,
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
      series: [44, 55, 13, 43],
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
