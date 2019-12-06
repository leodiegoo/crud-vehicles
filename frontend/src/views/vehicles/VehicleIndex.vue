<template>
  <section class="section">
    <crud-section-container>
      <h1 class="title">Vehicles</h1>
      <h2 class="subtitle">
        All vehicles registered in database
        <crud-button type="is-primary" @click="toForm">Add new vehicle</crud-button>
      </h2>
    </crud-section-container>
    <crud-button type="is-info" @click="editVehicle"><span>Edit selected</span></crud-button>
    <crud-button type="is-danger" @click="deleteVehicle"><span>Delete selected</span></crud-button>
    <crud-table ref="listVehicles" focusable :list-data="vehicles" :columns="columns" style="margin-top: 10px;"/>
  </section>
</template>

<script>
  import {db} from '../../main';

  export default {
    name: 'VehicleIndex',
    data: () => ({
      vehicles: [],
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
        },
        {
          field: 'plate',
          label: 'Plate',
        }
      ]
    }),
    firestore() {
      return {
        vehicles: db.collection('vehicle')
      }
    },
    methods: {
      toForm() {
        this.$router.push({path: '/vehicles/form'})
      },
      editVehicle($event) {
        let vehicleSelected = this.$refs.listVehicles.getSelected();
        if (vehicleSelected.id) {
          this.$router.push({path: '/vehicles/form', query: {id: vehicleSelected.id}})
        } else {
          _.debounce(() => {
            this.$toast.open({
              message: "You need to select a vehicle",
              type: 'is-warning',
              position: 'is-bottom',
            });
          }, 500)();
        }
      },
      deleteVehicle() {
        let vehicleSelected = this.$refs.listVehicles.getSelected();
        if (vehicleSelected.id) {
          this.$dialog.confirm({
            message: 'Delete vehicle (' + vehicleSelected.name + ')?',
            onConfirm: () => {
              const loadingComponent = this.$loading.open({
                container: null
              });
              db.collection('vehicle').doc(vehicleSelected.id).delete().then(() => {
                this.$toast.open({
                  message: 'Vehicle deleted successfully',
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
              message: "You need to select a vehicle",
              type: 'is-warning',
              position: 'is-bottom',
            });
          }, 500)();
        }
      }
    }
  }
</script>
