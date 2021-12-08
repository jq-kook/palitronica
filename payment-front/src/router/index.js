import Vue from 'vue'
import Router from 'vue-router'
import HelloCerealWorld from '@/components/HelloCerealWorld'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloCerealWorld',
      component: HelloCerealWorld
    }
  ]
})
