<template>
  <b-button :class="clazz" rounded :type="type" @click="click">
    <slot></slot>
  </b-button>
</template>
<script>
  export default {
    props: {
      type: {
        type: String,
        default: null
      },
      clazz: {
        type: String,
        default: null
      }
    },
    methods: {
      click() {
        // this.$notification.open('Clicked!!')
        this.$emit('click');
      }
    }
  }
</script>
