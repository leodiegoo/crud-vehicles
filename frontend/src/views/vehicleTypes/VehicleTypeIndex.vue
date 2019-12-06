<template>
  <section class="section">
    <crud-section-container>
      <h1 class="title">Vehicle Types</h1>
      <h2 class="subtitle">
        All types of vehicles registered in database
        <crud-button type="is-primary" @click="toForm">Add new type</crud-button>
      </h2>
    </crud-section-container>
    <crud-button type="is-info" @click="editVehicleType"><span>Edit selected</span></crud-button>
    <crud-button type="is-danger" @click="deleteVehicleType"><span>Delete selected</span></crud-button>
    <crud-table ref="listVehicleTypes" focusable :list-data="vehicles_types" :columns="columns"
                style="margin-top: 10px;"/>
  </section>
</template>

<script>
  import {db} from '../../main';

  export default {
    name: 'VehicleTypeIndex',
    data() {
      return {
        columns: [
          {
            field: 'id',
            label: 'ID',
            width: '40',
            numeric: true
          },
          {
            field: 'name',
            label: 'Name',
          },
          {
            field: 'desc',
            label: 'Description',
          }
        ],
        vehicles_types: []
      }
    },
    firestore() {
      return {
        vehicles_types: db.collection('vehicletype')
      }
    },
    methods: {
      toForm() {
        this.$router.push({path: '/vehicleTypes/form'})
      },
      editVehicleType($event) {
        let vehicleTypeSelected = this.$refs.listVehicleTypes.getSelected();
        if (vehicleTypeSelected.id) {
          this.$router.push({path: '/vehicleTypes/form', query: {id: vehicleTypeSelected.id}})
        } else {
          _.debounce(() => {
            this.$toast.open({
              message: "You need to select a type",
              type: 'is-warning',
              position: 'is-bottom',
            });
          }, 500)();
        }
      },
      deleteVehicleType() {
        let vehicleTypeSelected = this.$refs.listVehicleTypes.getSelected();
        if (vehicleTypeSelected.id) {
          this.$dialog.confirm({
            message: 'Delete type (' + vehicleTypeSelected.name + ')?',
            onConfirm: () => {
              const loadingComponent = this.$loading.open({
                container: null
              });
              db.collection('vehicletype').doc(vehicleTypeSelected.id).delete().then(() => {
                this.$toast.open({
                  message: 'Type deleted successfully',
                  type: 'is-success'
                });
              }).finally(() => {
                loadingComponent.close();
              })
            }
          })
        } else {
          _.debounce(() => {
            this.$toast.open({
              message: "You need to select a type",
              type: 'is-warning',
              position: 'is-bottom',
            });
          }, 500)();
        }
      }
    }
  }
</script>
