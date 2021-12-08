<template>
    <div class="postbody">
        <Guidebar></Guidebar>
        <span><img id="record" src="@/assets/recordlife.png"></span>
    </div>
</template>
<style>
.postbody #record{
    width: 300px;
    height: 50px;
}
</style>
<script>
import Guidebar from '@/components/Guidebar'
export default {
    name: 'Postword',
    components: {
    Guidebar
    },
    data() {
        
    }
}
</script>