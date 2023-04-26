<template>
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

        <pie-chart v-if="slide == 'Pie'"></pie-chart>
        <bar-chart v-else></bar-chart>
      </q-card>
      <q-card class="my-card" bordered>
        <q-card-section>
          <div class="text-h6">Time Series Data Comparison</div>
        </q-card-section>

        <splin-chart></splin-chart>
      </q-card>
    </div>

    <div class="col-xs-12 col-sm-6 col-md-6">
      <q-card class="my-card" style="height: 760px" bordered>
        <q-card-section>
          <div class="text-h6">Ich bins 1 Karte</div>
        </q-card-section>
      </q-card>
    </div>
  </q-page>
</template>

<script lang="ts">
import { Todo, Meta } from 'components/models';
import BarChart from 'components/BarChart.vue';
import PieChart from 'components/PieChart.vue';
import SplinChart from 'components/SplinChart.vue';

import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: 'IndexPage',
  components: { BarChart, PieChart, SplinChart },

  data() {
    return {};
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
  height: 370px
  margin: 20px
</style>
