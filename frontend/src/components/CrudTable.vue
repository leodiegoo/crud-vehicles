<template>
  <b-table focusable :data="listData" :columns="columns" :selected.sync="selected"/>
</template>

<script>
  export default {
    props: {
      listData: {
        type: Array,
        default: null
      },
      columns: {
        type: Array,
        default: null
      },
      focusable: {
        type: Boolean,
        default: false
      }
    },
    data: () => ({
      selected: {}
    }),
    methods: {
      getSelected() {
        return this.selected;
      },
      clearSelected() {
        this.selected = {};
      }
    }
  }
</script>
