<template>
  <b-field :label="label">
    <b-select :placeholder="placeholder" :value="value" @input="updateValue($event)">
      <option v-for="option in listData"
              :value="option[valueOption]"
              :key="option[valueOption]"
      >
        {{option[labelOption]}}
      </option>
    </b-select>
  </b-field>
</template>
<script>
  import CrudUpdateValueMixin from '../mixins/CrudUpdateValueMixin'

  export default {
    mixins: [CrudUpdateValueMixin],
    props: {
      listData: {
        type: Array
      },
      valueOption: {
        type: [String, Number]
      },
      labelOption: {
        type: String
      },
      placeholder: String,
      value: [String, Number, Boolean]
    }
  }
</script>
