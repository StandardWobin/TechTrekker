<template>
  <div class="q-pa-xl" style="height: 60vh; width: 100%">
    <l-map
      ref="map"
      class="map"
      :useGlobalLeaflet="false"
      :center="center"
      :zoom="zoom"
      @update:center="
        (center) => {
          this.center = center;
        }
      "
      @update:zoom="
        (zoom) => {
          this.zoom = zoom;
        }
      "
    >
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        layer-type="base"
        name='OpenStreetMap'
      ></l-tile-layer>

      <!-- <l-image-overlay
      id="imageOverlay"
        :zIndex="10000"
        @click="console.log('lala')"
       url="de.png" :bounds="[[49, 8], [51, 9]]" ></l-image-overlay>-->
    </l-map>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

// DON'T load Leaflet components here!
// Its CSS is needed though, if not imported elsewhere in your application.
import 'leaflet/dist/leaflet.css';
import { LMap, LTileLayer } from '@vue-leaflet/vue-leaflet';

export default defineComponent({
  name: 'LeaFletEndPoint',
  emit: ["click"],

  components: {
    LMap,
    LTileLayer
  },

  data() {
    return {
      zoom: 6,
      center: [51.220515, 9.997559],
      geojson: {
        type: 'FeatureCollection',
        features: [
          // ...
        ],
      },
      geojsonOptions: {},
    };
  },




  async beforeMount() {
    // HERE is where to load Leaflet components!
    const { circleMarker } = await import('leaflet/dist/leaflet-src.esm');




    // And now the Leaflet circleMarker function can be used by the options:
    this.geojsonOptions.pointToLayer = (feature, latLng) =>
      circleMarker(latLng, { radius: 8 });
    this.mapIsReady = true;
  },
});
</script>

<style>
#map {
  height: 100%;
  width: 100%;
}
</style>
