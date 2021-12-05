<template>
<div class="article_info">
    <Guidebar></Guidebar>
    <div class="article">
        <el-card class="box-card">
             <div slot="header" class="clearfix">
                    <img>
                    <span>作者名称</span>
            </div>
                 <div>正文</div>
        </el-card>

    </div>
    <div class="comment">

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
.article{
    margin-left:250px;
    margin-right:250px;
}
</style>