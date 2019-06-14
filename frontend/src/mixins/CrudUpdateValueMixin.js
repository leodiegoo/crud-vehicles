export default {
  props: {
    label: String,
  },
  methods: {
    updateValue: function (value) {
      this.$emit('input', value)
    }
  }
}
