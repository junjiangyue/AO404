<template>
<div>
    <Guidebar></Guidebar>
    <div class="noticehead"><img src="@/assets/back.png" id="backpng" @click="jumppage"></div>
    <div class="noticehead"><h2 style="color:#101010;margin-left:20px;margin-bottom: 0px;">通知</h2></div>
    <el-divider></el-divider>
    <el-card class="box-card">
        <div v-for="(item) in tabledata" :key="item.id" class="text-item">
            <div class="noticeblock">
                <h3>{{item.title }}</h3>
                <p>{{item.message1}}</p>
                <!-- <img @click="changeview" v-if="lay_type" src="@/assets/noticebottom.png" alt />
                <img @click="changeview" v-else src="@/assets/noticeup.png" alt /> -->
            </div>
        </div>
    </el-card>
    <div class="noticepicture"><img  src="@/assets/noticepic.png"></div>
</div>
</template>
<style>
.text {
    font-size: 14px;
}
.item {
    padding: 18px 0;
}
.box-card {
    width: 980px;
}
.el-card.is-always-shadow, .el-card.is-hover-shadow:focus, .el-card.is-hover-shadow:hover {
    -webkit-box-shadow: 0 0px 0px 0 rgb(0 0 0 / 10%);
    box-shadow: 0 0px 0px 0 rgb(0 0 0 / 10%);
}
.el-card {
    border: 0px solid #EBEEF5;
}
.noticepicture img{
    width: 380px;
    height: 300px;
    position: absolute;
    left: 980px;
    bottom: 180px;
}
.noticeblock{
    padding: 20px;
    width: 800px;
    height: 150px;
    margin-left: 100px;
    margin-top: 20px;
    margin-bottom: 10px;
      border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
.noticeblock:hover {
    height: 300px;
}
.noticeblock h3{
    margin-left: 10px;
    margin-top: 10px;
}
.noticeblock p{
    margin-left: 40px;
    margin-top: 20px;
    padding-right: 10px;
    font-size: 19px;
}
.noticeblock img{
    position: absolute;
    margin-left: 730px;
    margin-top: -100px;
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
    width: 65%;
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
        return {
            // activeName: '',
            // lay_type: 0,
            tabledata: [{
                id: 1,
                title: '通知',
                message1: '对不起，您于2021年12月3日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架',
                // message: '对不起，您于2021年12月3日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架对不起，您于2021年12月3日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架对不起，您于2021年12月3日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            },
            {
                id: 2,
                title: '通知',
                message1: '对不起，您于2021年12月3日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            },
            {
                id: 3,
                title: '通知',
                message1: '对不起，您于2021年12月4日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            },
            {
                id: 4,
                title: '通知',
                message1: '对不起，您于2021年12月5日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            },
            {
                id: 5,
                title: '通知',
                message1: '对不起，您于2021年12月6日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            },
            {
                id: 6,
                title: '通知',
                message1: '对不起，您于2021年12月7日发布的内容为“哈哈哈哈哈哈哈哈哈”的文章因为不符合平台要求被下架'
            }]
        }
    },
    methods: {
        jumppage() {
            this.$router.push('/creatorcenter')
        }
        // changeview() {
        //     if(this.lay_type == 0){
        //         this.lay_type = 1
        //     }else{
        //         this.lay_type = 0
        //     }
        // }
    }
}
</script>