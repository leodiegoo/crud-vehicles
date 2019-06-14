<template>
  <b-field :label="label">
    <b-input :value="value" @input="updateValue($event)"></b-input>
  </b-field>
</template>
<script>
  import CrudUpdateValueMixin from '../mixins/CrudUpdateValueMixin'

  export default {
    mixins: [CrudUpdateValueMixin],
    props: {
      value: [String, Number, Boolean]
    }
  }
</script>
