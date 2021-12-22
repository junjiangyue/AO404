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
import Admin from '@/components/Admin'
import ArticleManage from '@/components/ArticleManage'
import UserManage from '@/components/UserManage'
import FeedbackManage from '@/components/FeedbackManage'
import FollowList from '@/components/FollowList'
import FanList from '@/components/FanList'
import UserInfo from '@/components/UserInfo'
import AccountSecurity from '@/components/AccountSecurity'
import OtherUserPage from '@/components/OtherUserPage'
import ForgetPassword from '@/components/ForgetPassword'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
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
      component: Main,
      meta: {  requireAuth:true  }
    },
    {
      path: '/creatorcenter',
      name: 'CreatorCenter',
      component: CreatorCenter,
      meta: {  requireAuth:true  }
    },
    {
      path: '/Discover',
      name: 'Discover',
      component: Discover,
      meta: {  requireAuth:true  }
    },
    {
      path: '/advicefeedback',
      name: 'AdviceFeedback',
      component: AdviceFeedback,
      meta: {  requireAuth:true  }
    },{
      path: '/notice',
      name: 'Notice',
      component:Notice,
      meta: {  requireAuth:true  }
    },{
      path:'/ArticleInfo',
      name:'ArticleInfo',
      component:ArticleInfo,
      meta: {  requireAuth:true  }
    },{
      path:'/PersonalPage',
      name:'PersonalPage',
      component:PersonalPage,
      meta: {  requireAuth:true  }
    },{
      path:'/Postword',
      name:'Postword',
      component:Postword,
      meta: {  requireAuth:true  }
    },{
      path:'/AdministerLogin',
      name:'AdministerLogin',
      component:AdministerLogin
    },{
      path:'/Admin',
      name:'Admin',
      component:Admin
    },{
      path:'/UserManage',
      name:'UserManage',
      component:UserManage
    },{
      path:'/ArticleManage',
      name:'ArticleManage',
      component:ArticleManage
    },{
      path:'/FeedbackManage',
      name:'FeedbackManage',
      component:FeedbackManage
    },{
      path:'/Tag',
      name:'Tag',
      component:Tag,
      meta: {  requireAuth:true  }
    },{
      path:'/FollowList',
      name:'FollowList',
      component:FollowList,
      meta: {  requireAuth:true  }
    },{
      path:'/UserInfo',
      name:'UserInfo',
      component:UserInfo,
      meta: {  requireAuth:true  }
    },{
      path:'/AccountSecurity',
      name:'AccountSecurity',
      component:AccountSecurity,
      meta: {  requireAuth:true  }
    },{
      path:'/FanList',
      name:'FanList',
      component:FanList,
      meta: {  requireAuth:true  }
    },{
      path:'/OtherUserPage',
      name:'OtherUserPage',
      component:OtherUserPage,
      meta: {  requireAuth:true  }
    },{
      path:'/ForgetPassword',
      name:'ForgetPassword',
      component:ForgetPassword,
      meta: {  requireAuth:true  }
    }
  ]
})
