import Vue from 'vue'
import Router from 'vue-router'
import Discover from "@/components/Discover"
import Tag from "@/components/Tag"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Tag',
      component: Tag
    }
  ]
})
