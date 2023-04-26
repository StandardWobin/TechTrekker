<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title>

            <q-btn @click="$router.replace('/')" flat style="color: #FFFFFF" label="TechTrekker (Arbeitstitel)" />




        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer>
      <footer-content></footer-content>
    </q-footer>
  </q-layout>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import FooterContent from 'components/FooterContent.vue';

export default defineComponent({
  name: 'MainLayout',

  components: {
    FooterContent,
  },
});
</script>
