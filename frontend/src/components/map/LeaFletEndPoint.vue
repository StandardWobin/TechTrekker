<template>
  <div class="q-pa-xl" style="height: 70vh; width: 100%">
    <l-map
      ref="map"
      class="map"
      :useGlobalLeaflet="false"
      :center="center"
      :zoom="zoom"
    >
      <l-tile-layer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        layer-type="base"
        name="OpenStreetMap"
      ></l-tile-layer>

      <l-geo-json :geojson="geojson" :options="geojsonOptions" />
    </l-map>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

// DON'T load Leaflet components here!
// Its CSS is needed though, if not imported elsewhere in your application.
import 'leaflet/dist/leaflet.css';
import { LMap, LGeoJson, LTileLayer } from '@vue-leaflet/vue-leaflet';

export default defineComponent({
  name: 'LeaFletEndPoint',

  components: {
    LMap,
    LGeoJson,
    LTileLayer,
  },

  data() {
    return {
      zoom: 2,
      center: [-20.38, 47.5],
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
