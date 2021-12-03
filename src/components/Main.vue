<template>
  <div class="hello">
    <Guidebar></Guidebar>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'Main',
  components: {
    Guidebar
    },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
