<template>
  <div class="q-pt-md" style="text-align: center">
    <q-chip
      @click="select[0] = !select[0]"
      :selected="select[0]"

      :style="'background:' + colors[0]"

      text-color="white"
    >
      Vue
    </q-chip>

    <q-chip
      @click="select[1] = !select[1]"
      :selected="select[1]"
      :style="'background:' + colors[1]"
      text-color="white"
    >
      Angular
    </q-chip>
    <q-chip
      @click="select[2] = !select[2]"
      :selected="select[2]"
      :style="'background:' + colors[2]"
      text-color="white"
    >
      React
    </q-chip>
    <q-chip
      @click="select[3] = !select[3]"
      :selected="select[3]"
      :style="'background:' + colors[3]"
      text-color="white"
    >
      Bootstrap
    </q-chip>
  </div>
  <q-page class="row full-width wrap justify-center">
    <div class="col-xs-12 col-sm-6 col-md-6">
      <q-card class="my-card" bordered>
        <q-card-section>
          <div class="text-h6">TOP LIST and BAR CHART</div>
          <div class="row justify-center">
            <q-btn-toggle
              glossy
              v-model="slide"
              :options="[
                { label: 'Pie', value: 'Pie' },
                { label: 'Bar', value: 'Bar' },
              ]"
            />
          </div>
        </q-card-section>
        <pie-chart v-if="slide == 'Pie'" :select="select" :colors="colors"></pie-chart>
        <bar-chart v-else :select="select" :colors="colors"></bar-chart>
      </q-card>

      <q-card class="my-card" bordered>
        <q-card-section>
          <div class="text-h6">Time Series Data Comparison</div>
        </q-card-section>
        <splin-chart :select="select" :colors="colors"></splin-chart>
      </q-card>
    </div>

    <div class="col-xs-12 col-sm-6 col-md-6">
      <q-card class="my-card" style="height: 660px" bordered>
        <q-card-section>
          <div class="text-h6">Ich bins 1 Karte</div>
        </q-card-section>
        <lea-flet-end-point :select="select" :colors="colors"></lea-flet-end-point>
      </q-card>
    </div>
  </q-page>
</template>

<script lang="ts">
import { Todo, Meta } from 'components/models';
import BarChart from 'components/charts/BarChart.vue';
import PieChart from 'components/charts/PieChart.vue';
import SplinChart from 'components/charts/SplinChart.vue';

import LeaFletEndPoint from 'components/map/LeaFletEndPoint.vue';
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'IndexPage',
  components: { BarChart, PieChart, SplinChart, LeaFletEndPoint },

  data() {
    return {
      select: [false, true, true, false],
      colors: ['#008b3f', '#FF7300', '#4B15CA', '#92AC35'],
    };
  },

  setup() {
    const todos = ref<Todo[]>([
      {
        id: 1,
        content: 'ct1',
      },
      {
        id: 2,
        content: 'ct2',
      },
      {
        id: 3,
        content: 'ct3',
      },
      {
        id: 4,
        content: 'ct4',
      },
      {
        id: 5,
        content: 'ct5',
      },
    ]);
    const meta = ref<Meta>({
      totalCount: 1200,
    });
    return { todos, meta, slide: ref('Pie') };
  },
});
</script>

<style lang="sass" scoped>
.my-card
  width: auto
  padding: 10px
  height: 320px
  margin: 20px
</style>
