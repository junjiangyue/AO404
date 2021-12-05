import Vue from 'vue'
import Router from 'vue-router'
import Discover from "@/components/Discover"
import Tag from "@/components/Tag"
import Main from '@/components/Main'
import Guidebar from '@/components/Guidebar'
import PersonalMenu from '@/components/PersonalMenu'
import PersonalPage from '@/components/PersonalPage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      //name: 'PersonalPage',
      //component: PersonalPage
      //name: 'Tag',
      //component: Tag
      name: 'Main',
      component: Main
    }
  ]
})
