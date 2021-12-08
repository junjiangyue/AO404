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
import CreatorCenter from '@/components/CreatorCenter'
import AdviceFeedback from '@/components/AdviceFeedback'
import Notice from '@/components/Notice'
import ArticleInfo from '@/components/ArticleInfo'
import Postword from '@/components/Postword'
import AdministerLogin from '@/components/AdministerLogin'

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
      path: '/Main',
      name: 'Main',
      component: Main
    },
    {
      path: '/creatorcenter',
      name: 'CreatorCenter',
      component: CreatorCenter
    },
    {
      path: '/Discover',
      name: 'Discover',
      component: Discover
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
      path:'/ArticleInfo',
      name:'ArticleInfo',
      component:ArticleInfo
    },{
      path:'/PersonalPage',
      name:'PersonalPage',
      component:PersonalPage
    },{
      path:'/Postword',
      name:'Postword',
      component:Postword
    },{
      path:'/',
      name:'AdministerLogin',
      component:AdministerLogin
    }
  ]
})
