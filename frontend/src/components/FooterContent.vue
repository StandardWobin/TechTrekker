<template>
  <div style="display:flex;   justify-content: center;">
    <q-btn @click="$router.replace('/legal')" flat style="color: #FFFFFF" label="Imprint" />
    <p class="q-mx-xs q-pt-md">|</p>
    <q-btn @click="$router.replace('/privacy')" flat style="color: #FFFFFF" label="Privacy" />
  </div>
</template>

<script lang="ts">
import { defineComponent, P } from 'vue';

export default defineComponent({
  name: 'FooterContent',
});
</script>
