<template>
    <div class="postbody">
        <Guidebar></Guidebar>
        <span><img id="record" src="@/assets/recordlife.png"></span>
        <div class="postmain">
            <div class="postwordblock" id="userimg">
                <img id="userpic" src="@/assets/mlogo.png"/>
            </div>
            <div class="postwordblock" id="wordedit" v-bind:style="{ height: moveblock + 'px'}">
                <h2>{{user_name}}</h2>
                <el-input id="inputtitle" v-model="input" placeholder="请输入内容"></el-input>
                <el-input id="inputcontent" type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea"></el-input>
                
                <div class="addimgbutton" @click="changetag" id="tag">
                <div class="picimg">
                <el-upload action="#" list-type="picture-card" :auto-upload="false">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{file}">
                        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
                        <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                <i class="el-icon-zoom-in"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                                <i class="el-icon-download"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                                <i class="el-icon-delete"></i>
                            </span>
                        </span>
                    </div>
                </el-upload>
                </div>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                </div>
                <div class="dropmenu" id="tag" v-bind:style="{ marginTop: movetag + 'px'}">
                    <img id="addtitleimg" src="@/assets/addtitle.png">
                </div>
                <el-input class="inputtagblock" id="tag" @keyup.enter.native="onSubmit" v-bind:style="{ marginTop: movetag + 'px'}" v-model="inputtag" placeholder="请输入内容"></el-input>
                <!-- <div class="blocktian" id="tian" v-show="popup"></div> -->
                <el-tag class="blocktian" @close="handleClose(tag)" v-for="tag in tags" :key="tag.name" closable :type="tag.type">{{tag.name}}</el-tag>
                <br>
                <el-button id="canclebutton" @click="canclecontent">取消</el-button>
                <el-button id="affirmbutton" @click="postcontent">发布</el-button>
            </div>
        </div>
    </div>
</template>
<style>
.blocktian{
    display: inline-block;
    vertical-align: top;
    margin-top: 20px;
}
#tian{
    width:407px;
    height: 20px;
}
.el-tag {
    /* margin-right: 20px; */
    margin-left: 40px;
}
#canclebutton{
margin-left: 40px;
margin-top: 20px;
color: white;
background-color: #939498;
}
#affirmbutton{
    color: white;
    background-color: #E33B2B;
margin-left: 575px;
margin-top: 20px;
}
.picimg{
    width: 720px;
}
.addimgbutton{
    width: 5%;
    margin-left: 40px;
    margin-top: 20px;
}
.el-upload--picture-card {
    width: 40px;
    height: 40px;
    background:url('../assets/addpic.png') no-repeat;
    background-size: 100% 100%;
    display: grid;
}
.el-icon-plus:before {
    content: none;
}
#tag{
    display: inline-block;
    vertical-align: top;
}
.inputtagblock{
    width: 30%;
}
.dropmenu{
    width: 5%;
    border-radius: 4px;
    border: 1px solid #DCDFE6;
    margin-left: 390px;
    margin-top: 20px;
}
#addtitleimg{
    width:30px;
    height: 30px;
    margin-left:4px;
    margin-top:3px;
}
.dropmenu:hover{
    border-color: #C0C4CC;
}
.postbody #record{
    width: 380px;
    height: 50px;
    margin-left: 50px;
    margin-top: 20px;
}
.postwordblock{
    display: inline-block;
    vertical-align: top;
}
#userpic{
    width:50px;
    height:50px;
    margin-left: 250px;
    margin-top: 30px;
}
#wordedit{
    width:800px;
    margin-left: 50px;
    margin-top: 30px;
    border-radius: 4px;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
h2{
    margin-left: 40px;
}
#inputtitle{
    width:90%;
    margin-left: 40px;
}
#inputcontent{
    width:90%;
    margin-left: 40px;
    margin-top: 20px;
    height: 200px;
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
        return {
            // popup: 0,
            user_name: '原神',
            input: '',
            textarea: '',
            inputtag: '',
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,
            movetag: 20,
            moveblock: 480,
            count: 0,
            tags: [],
            number: 0,
            userId: 40400001,
            tagId: 12,
        }
    },
    methods: {
        postcontent() {
            if(this.textarea&&this.inputtag&&this.input != '') {
            this.$axios({
                method:"post",
                url: 'http://47.102.194.89:8080/article/publishArticle',
                params: {
                    articleHeading: this.input,
                    articleContent: this.textarea,
                    tagId: this.tagId,
                },
                headers: { token:window.sessionStorage.getItem("token")},
            }).then(res=>{
                console.log(res);
                if(res.status == 200) {
            this.$notify({
          title: '成功',
          message: '发布成功，感谢您的使用！',
          type: 'success'
        });
          }
            })}
            else {
                console.log("失败");
                this.$notify.error({
          title: '错误',
          message: '禁止发送空内容贴'
        });
      }
            
        },
        handleCommand(command) {
        this.$message('click on item ' + command);
      },
       handleRemove(file) {
        console.log(file);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
      handleDownload(file) {
        console.log(file);
      },
      changetag() {
          if( this.count % 8 == 0){
              this.movetag += 155;
              this.moveblock += 160;
          }
          this.count += 1;
          console.log(this.count);
      },
      onSubmit() {
        //   this.popup = 1;
        if(this.number == 0){
            this.moveblock +=60;
            this.number =1;
        }
          console.log(this.inputtag);
          console.log(this.tags);
          this.tags.push({name: this.inputtag,type:''});
          console.log(this.tags);
          this.inputtag = '';
      },
      handleClose(tag) {
          this.tags.splice(this.tags.indexOf(tag), 1);
          if(this.tags.length == 0){
              this.moveblock -=60;
          }
          },
          canclecontent() {
              this.input = '';
              this.textarea = '';
              this.inputtag = '';
              this.$notify.info({
          title: '消息',
          message: '取消发布内容'
        });
          }
    }
}
</script>