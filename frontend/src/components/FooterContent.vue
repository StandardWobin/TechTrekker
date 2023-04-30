<template>
  <div style="display: flex; justify-content: center">
    <q-btn
      @click="$router.replace('/legal')"
      flat
      style="color: #ffffff"
      label="Imprint"
    />
    <p class="q-mx-xs q-pt-md">|</p>
    <q-btn
      @click="$router.replace('/privacy')"
      flat
      style="color: #ffffff"
      label="Privacy"
    />
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'FooterContent',
});
</script>
