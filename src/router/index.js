import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import CreatorCenter from '@/components/CreatorCenter'
import AdviceFeedback from '@/components/AdviceFeedback'
import Notice from '@/components/Notice'
import Guidebar from '@/components/Guidebar'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
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
    }
  ]
})
