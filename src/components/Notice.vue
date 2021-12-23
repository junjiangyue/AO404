<template>
<div>
    <Guidebar></Guidebar>
    <div class="noticehead"><img src="@/assets/back.png" id="backpng" @click="jumppage"></div>
    <div class="noticehead"><h2 style="color:#101010;margin-left:20px;margin-bottom: 0px;">通知</h2></div>
    <el-divider></el-divider>
    <el-card class="box-card">
        <div v-for="(item) in resultList" :key="item.index" class="text-item">
            <div class="noticeblock">
                <h3>通知</h3>
                <p>{{item.messageHead}}</p>
                <!-- <p>{{item.message}}</p> -->
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
            userId: 123,
            tabledata: [],
            feedIdList:[],
            resultList:[]

        }
    },
    mounted:function() {
        this.$axios({
            method:"get",
            url: 'http://47.102.194.89:8080/feedback/getUserfeedback',
            params:{},
            headers: { token:window.sessionStorage.getItem("token")},
        }).then(res=>{
            console.log("test")
            console.log(res.data.data.feedbackList);
            this.feedIdList = res.data.data.feedbackList;
            console.log(this.feedIdList)
            console.log(res.data.data.feedbackList.length)
            var count = 0;
            for(;count<res.data.data.feedbackList.length;count++){
            var num;
            num = res.data.data.feedbackList[count].feedbackId;
            // consolelog(this.feedIdList[0].feedbackId)
            console.log(num)
            this.$axios({
                method:"get",
                url:'http://47.102.194.89:8080/feedback/getanswer',
                params:{
                    feedbackId:num
                },
                headers: { token:window.sessionStorage.getItem("token")},
            }).then(res=>{
                console.log(res)
                console.log(res.data.data.answer)
                if(res.data.data.answer!=null){
                this.resultList.push({messageHead: res.data.data.answer.messageHead});
                }
            })
            }
            console.log("lll")
            console.log(this.resultList)
        })


        // this.$axios({
        //     method:"get",
        //     url:'http://47.102.194.89:8080/feedback/getfeedback',
        //     params: {},
        //     headers: { token:window.sessionStorage.getItem("token")},
        // }).then(res=>{
        //     console.log(res);
        //     // console.log(res.data.data.myNotice);
        //     this.tabledata = res.data.data.feedbackList;
        //     // this.tabledata.title = res.data.data.myNotice.noticeTitle;
        //     // console.log(res.data.data.myNotice.array);
        // })

        // var num;
        // num = this.feedIdList[0].feedbackId;
        // console.log(this.feedIdList[0].feedbackId)
        // console.log(num)
        // console.log("daying")
        // this.feedIdList.forEach(item => console.log(item))
        // this.feedIdList.array.forEach(element => {
        //     console.log(element)
        // });
        // this.$axios({
        //     method:"get",
        //     url:'http://47.102.194.89:8080/feedback/getanswer',
        //     params:{},
        //     headers: { token:window.sessionStorage.getItem("token")},
        // }).then(res=>{
        //     console.log(res)
        // })

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