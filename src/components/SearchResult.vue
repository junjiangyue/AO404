<template>
    <div class="postbody">
        <Guidebar @searchEvent='parentEvent($event)'></Guidebar>
        <!-- < @searchEvent='parentEvent($event)'></child> -->
        <div class="searchResultBody">
            <div class="result" id="userResult"></div>
            <div class="result" id="tagResult"></div>
        </div>
    </div>
</template>
<style>
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
    border-radius: 4px;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
#tagResult {
    width: 33%;
    height: 100%;
    margin-left: 13px;
    /* border: 1px solid black; */
    border-radius: 4px;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
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
            searchContent1: ''
        }
    },
    methods: {
        // this.searchContent = this.$route.params.searchcontent
        parentEvent(data) {
            console.log(data);
            this.searchContent1 = data;
        }
    },
    mounted:function() {
        // this.parentEvent($event);
        // console.log(data)
        // this.parentEvent(data);
        this.searchContent1 = this.$route.params.searchcontent;
        console.log(this.searchContent1)
    }
}
</script>