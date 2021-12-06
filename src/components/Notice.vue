<template>
<div>
<!-- <div class="notice"> -->
    <Guidebar></Guidebar>
    <div class="noticehead"><img src="@/assets/back.png" id="backpng" @click="jumppage"></div
    ><div class="noticehead"><h2 style="color:#101010;margin-left:20px;margin-bottom: 0px;">通知</h2></div>
    <el-divider></el-divider>
    <div class="noticeblock"></div>
    <div class="noticeblock"></div>
    <div class="noticeblock"></div>
    <div class="noticeblock"></div>
    <div class="noticeblock"></div>
</div>
<!-- </div> -->
</template>
<style>
.noticeblock{
    box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
    border: 1px solid rgba(187, 187, 187, 100);
    border-radius: 10px;
    width: 700px;
    height: 150px;
    margin-left: 100px;
    margin-top: 20px;
    margin-bottom: 10px;
}
.noticehead{
    display: inline-block;
    vertical-align: top;
}
.noticehead #backpng{
    width:30px;
    height:30px;
    margin-top: 24px;
    margin-left: 50px;
}
.el-divider--horizontal {
    display: block;
    height: 1px;
    width: 60%;
    left: 4%;
    margin: 12px 0;
}
</style>
<script>
import Guidebar from '@/components/Guidebar'
export default {
    name: 'Notice',
    components: {
        Guidebar
    },
    data() {
        
    },
    methods: {
        jumppage() {
            this.$router.push('/creatorcenter')
        }
    }
}
</script>