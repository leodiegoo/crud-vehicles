<template>
  <div id="app">
    <nav class="navbar is-primary" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
        <a class="navbar-item" href="/">
          CRUD Vehicles
        </a>
        <a class="navbar-item" href="/#/vehicles">
          Vehicles
        </a>
        <a class="navbar-item" href="/#/vehicleTypes">
          Vehicle Types
        </a>
      </div>
    </nav>
    <div id="content" class="content">
      <router-view/>
    </div>
  </div>
</template>


<script>
  export default {
    name: 'App'
  }
</script>

<style>
  #app {
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
</style>
