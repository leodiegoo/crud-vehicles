import Vue from 'vue'
import Router from 'vue-router'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'

import VehicleIndex from '@/views/vehicles/VehicleIndex'
import VehicleForm from '@/views/vehicles/VehicleForm'
import VehicleTypeForm from '@/views/vehicleTypes/VehicleTypeForm'
import VehicleTypeIndex from '@/views/vehicleTypes/VehicleTypeIndex'

import CrudComponents from '../components'

Vue.use(Buefy, {
  defaultContainerElement: "#content"
});
Vue.use(Router);
Vue.use(CrudComponents);

const routes = [
  {path: '/vehicles', name: 'VehicleIndex', component: VehicleIndex},
  {path: '/vehicles/form', name: 'VehicleForm', component: VehicleForm},
  {path: '/vehicleTypes', name: 'VehicleTypeIndex', component: VehicleTypeIndex},
  {path: '/vehicleTypes/form', name: 'VehicleTypeForm', component: VehicleTypeForm}
];

export default new Router({
  routes
})
