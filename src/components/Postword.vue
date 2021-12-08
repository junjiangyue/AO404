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
                <el-input class="inputtagblock" id="tag" v-bind:style="{ marginTop: movetag + 'px'}" v-model="inputtag" placeholder="请输入内容"></el-input>
                <el-tag v-for="tag in tags" :key="tag.name" closable :type="tag.type">{{tag.name}}</el-tag>
                <el-button id="canclebutton">取消</el-button>
                <el-button id="affirmbutton">发布</el-button>
            </div>
        </div>
    </div>
</template>
<style>
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
            tags: [
                { name: '标签一', type: '' },
                { name: '标签二', type: 'success' },
                { name: '标签三', type: 'info' },
                { name: '标签四', type: 'warning' },
                { name: '标签五', type: 'danger' }
                ]
        }
    },
    methods: {
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
              this.moveblock += 135;
          }
          this.count += 1;
          console.log(this.count);
      }
    }
}
</script>