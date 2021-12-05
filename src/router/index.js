import Vue from 'vue'
import Router from 'vue-router'
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
