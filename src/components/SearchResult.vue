<template>
    <div class="postbody">
        <Guidebar @searchEvent='parentEvent($event)'></Guidebar>
        <!-- < @searchEvent='parentEvent($event)'></child> -->
        <div class="searchResultBody">
            <div class="result" id="userResult">
                <el-card class="box1-card">
                    <div v-for="(item) in usertabledata" :key="item.id" class="text-item">
                        <div class="userBlock">
                            <h3 class="userMessage">用户名：<el-button style="font-size: 20px" type="text" @click="openOtherUserPage(item.userId)">{{item.userName}}</el-button></h3>
                            <p class="userMessage">{{item.userAvatar}}</p>
                        </div>
                    </div>
                </el-card>
            </div>
            <div class="result" id="tagResult">
                <!-- <h2>{{this.searchContent1}}</h2><p>相关的标签</p> -->
                <!-- <el-card class="box2-card">
                    <img style="margin:20px 20px;width:30px;height:30px;" src="@/assets/tag.png" /><h2 style="font-size:25px;" class="samerow">{{this.searchContent1}}</h2><p style="font-size:20px;color:grey;margin-left:10px;" class="samerow">相关的标签</p>
                    <div v-for="(item) in tagtabledata" :key="item.id" class="text-item">
                        <div class="tagBlock">
                            <div class="alignment" style="width:230px;"><h3 style="margin-left:20px;" class="tagMessage">{{item.tagName}}</h3></div>
                            <div class="alignment"><p style="margin-top:17px;font-size:18px;" class="tagMessage">热度：{{item.articleNum}}</p></div>
                        </div>
                    </div>
                </el-card> -->
                <img style="margin:20px 20px;width:30px;height:30px;" src="@/assets/tag.png" /><h2 style="font-size:25px;" class="samerow">{{this.searchContent1}}</h2><p style="font-size:20px;color:grey;margin-left:10px;" class="samerow">相关的标签</p>
                <el-table ref="tagtabledata" :data="tagtabledata" highlight-current-row @current-change="handleCurrentChange" style="width: 100%">
                    <el-table-column type="" label="" width="50"></el-table-column>
                    <el-table-column property="tagName" label="标签" width="120"></el-table-column>
                    <el-table-column property="articleNum" label="热度" width="120"></el-table-column>
                    <!-- <el-table-column property="address" label="地址"></el-table-column> -->
                </el-table>
                <!-- <div style="margin-top: 20px">
                    <el-button @click="setCurrent(tableData[1])">选中第二行</el-button>
                    <el-button @click="setCurrent()">取消选择</el-button>
                </div> -->



            </div>
        </div>
    </div>
</template>
<style>
.userBlock {
    width:100px;
}
.el-table__header-wrapper {
    height: 50px;
}
.alignment {
    display: inline-block;
    vertical-align: top;
}
.userBlock {
    width: 90%;
    height: 80px;
    /* border: 1px solid #ffffffc2; */
    background-color: rgba(240, 240, 240, 0.822);
    margin-bottom: 15px;
    border-radius: 4px;
    margin-left: 20px;
}
.samerow {
    display: inline-block;
    vertical-align: top;
}
.userMessage {
    display: inline-block;
    vertical-align: top;
    font-size: 18px;
}
.tagMessage {
    display: inline-block;
    vertical-align: top;
}
.searchResultBody {
    width: 75%;
    height: 500px;
    margin-left: 12%;
    margin-top: 80px;
    /* border: 1px solid black; */
}
.result {
    display: inline-block;
    vertical-align: top;
}
#userResult {
    width:65%;
    height: 100%;
    /* border: 1px solid black; */
    /* border: 1px solid rgba(187, 187, 187, 100);
    border-radius: 10px; */
    /* border-radius: 4px;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%); */
}
#tagResult {
    width: 33%;
    height: 100%;
    margin-left: 13px;
    /* border: 1px solid black; */
    /* border-radius: 4px;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%); */
}
</style>
<script>
import Guidebar from '@/components/Guidebar'
export default {
    name: 'SerachResult',
    components: {
    Guidebar
    },
    data() {
        return {
            searchContent1: '',
            usertabledata:[],
            tagtabledata:[]
        }
    },
    methods: {
        // handleCurrentChange() {

        // },
        setCurrent(row) {
        this.$refs.tagtabledata.setCurrentRow(row);
      },
      handleCurrentChange(val) {
        this.currentRow = val;
        console.log(this.currentRow)
        // tag(index, row) {
        // console.log('index:',index);
        // console.log('tagID:',this.tableData[index].tagId);
        this.$router.push({name:'Tag',params:{tagID:this.currentRow.tagId}});
    //   },
      },
        // this.searchContent = this.$route.params.searchcontent
        parentEvent(data) {
            console.log(data);
            this.searchContent1 = data;
            this.$axios({
            method:"post",
            url: 'http://47.102.194.89:8080//search/search',
            params: {
                searchKey: this.searchContent1,
            },
            headers: { token:window.sessionStorage.getItem("token")},
        }).then(res=>{
            console.log(res);
            this.usertabledata = res.data.data.userResult;
            this.tagtabledata = res.data.data.tagResult;
            console.log(this.usertabledata);
            console.log(this.tagtabledata);
        })
        },
        openOtherUserPage(id){
            console.log('打开的userid',id);
            if(id==this.userId){
                console.log('我的id');
                this.$router.push({path:'/PersonalPage'});
            } else {
                console.log('别人的id');
                this.$router.push({name:'OtherUserPage',params:{userID:id}});
            }
        }
    },
    mounted:function() {
        // this.parentEvent($event);
        // console.log(data)
        // this.parentEvent(data);
        this.searchContent1 = this.$route.params.searchcontent;
        console.log(this.searchContent1);
        this.$axios({
            method:"post",
            url: 'http://47.102.194.89:8080//search/search',
            params: {
                searchKey: this.searchContent1,
            },
            headers: { token:window.sessionStorage.getItem("token")},
        }).then(res=>{
            console.log(res)
            this.usertabledata = res.data.data.userResult;
            this.tagtabledata = res.data.data.tagResult;
        });
        this.$axios({
            method:"get",
            url: 'api/user/myInfo',
            headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
            console.log('我的信息数据：', res.data);
            this.userId=res.data.data.userId;
            console.log('userId',this.userId);
        },err=>{
            console.log(err);
        })
    }
}
</script>