<template>
<div class="article_info">
    <Guidebar></Guidebar>
    <div class="article">
        <el-card class="box-card">
             <div slot="header" class="clearfix">
                    <li><img class="writer_avatar" src="@/assets/mlogo.png"/></li>
                    <li><span class="writer_name">作者名称</span></li>
            </div>
            <div class="content">
                <div class="title">标题</div>
                <div class="time">发布时间</div>
                <div class="article_content">
                    <p>【我用早餐叫醒你】</p>
                    <p>弱者才会一蹶不振，我要逆风翻盘。</p>
                    <p>今日份元气早餐：蛋花酒酿/XO酱配法棍🥖/糖心苹果</p>
                    <img class="picture" src="@/assets/arabica-1300.png" width="100%" height="100%"/>
                 </div>
                <div class="tag">#标签</div>
            </div>
            <el-divider></el-divider>
            <div class="comments">
                <el-card class="comments-card">
                    <div class="cmter_info">
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <span class="writer_name">昵称</span>
                    </div>
                    <div class="comment_content">
                     太棒啦！   
                    </div>
                    <div class="comment_time">
                     评论时间  
                    </div>
                </el-card>
            </div>
        </el-card>

    </div>
</div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
    name:'AricleInfo',
    components: {
    Guidebar
    }
}
</script>

<style>
.writer_avatar{
    width:50px;
    height:50px;
}
.article{
    background-color: #EFEEEE;
    min-width: 1400px;
}
li{
    list-style-type:none;
    text-align: center;
}
.comments-card{
    width: auto;
    padding-left: 20px;
}
.title{
    font-size: 20px;
    font-weight:bold;
    margin-bottom: 10px;
}
.time{
    margin-bottom: 10px;
    font-size: 12px;
    color:#939498;
}
.comment_time{
    margin-top: 10px;
    font-size: 12px;
    color:#939498;
    margin-left: 40px;
}
.article_content{
     margin-bottom: 50px;
     font-family: "Hiragino Sans GB","Microsoft YaHei",\5FAE\8F6F\96C5\9ED1,tahoma,arial,simsun,\5B8B\4F53;
     font-size: 14px;
}
.content{
    padding-left:50px;
    padding-right:50px;
}
.box-card{
    margin-left:400px;
    margin-right:400px;

}
.writer_name{
    font-size: 14px;
    font-weight:bold;
}
.cmter_info{
    margin-bottom: 10px;
}
.comment_content{
    margin-left: 40px;
}
</style>