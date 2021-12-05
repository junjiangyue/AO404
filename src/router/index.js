import Vue from 'vue'
import Router from 'vue-router'
import Discover from "@/components/Discover"
import Tag from "@/components/Tag"
import Main from '@/components/Main'
import Guidebar from '@/components/Guidebar'
import PersonalMenu from '@/components/PersonalMenu'
import PersonalPage from '@/components/PersonalPage'
import Login from '@/components/Login'
import SignUp from '@/components/SignUp'
import Main from '@/components/Main'
import CreatorCenter from '@/components/CreatorCenter'
import AdviceFeedback from '@/components/AdviceFeedback'
import Notice from '@/components/Notice'
import ArticleInfo from '@/components/ArticleInfo'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/SignUp',
      name: 'SignUp',
      component: SignUp
    },
    {
<<<<<<< HEAD
      path: '/Main',
=======
      path: '/',
      //name: 'PersonalPage',
      //component: PersonalPage
      //name: 'Tag',
      //component: Tag
>>>>>>> b8ff340718fd5d9ac390f4f759aea4a90cbd0d53
      name: 'Main',
      component: Main
    },
    {
      path: '/creatorcenter',
      name: 'CreatorCenter',
      component: CreatorCenter
    },
    {
      path: '/advicefeedback',
      name: 'AdviceFeedback',
      component: AdviceFeedback
    },{
      path: '/notice',
      name: 'Notice',
      component:Notice
    },{
      path:'/',
      name:'ArticleInfo',
      component:ArticleInfo
    }
  ]
})
