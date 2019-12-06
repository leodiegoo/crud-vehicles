// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueLodash from 'vue-lodash'

import firebase from 'firebase/app'
import 'firebase/firestore'
import { firestorePlugin } from 'vuefire'

Vue.use(firestorePlugin)

export const db = firebase.initializeApp({projectId: 'vehicle-90e43'}).firestore()

Vue.config.productionTip = false;
const production = process.env.NODE_ENV === 'production';

let splitURL = window.location.href.split('/');
const baseURL = production ? `${splitURL[0]}/api/v1/` : 'http://localhost:8080/api/v1/';
Vue.use(VueAxios, axios.create({
  baseURL: baseURL
}));
Vue.use(VueLodash);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});
