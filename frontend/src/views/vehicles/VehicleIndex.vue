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
    <crud-table ref="listVehicles" focusable :list-data="listData" :columns="columns" style="margin-top: 10px;"/>
  </section>
</template>

<script>
  export default {
    name: 'VehicleIndex',
    data: () => ({
      listData: [],
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
    created() {
      this.$nextTick(() => {
        this.updateGrid();
      });
    },
    methods: {
      toForm() {
        this.$router.push({path: '/vehicles/form'})
      },
      updateGrid() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        this.axios(`/vehicles`).then(resp => {
          if (resp.data.success) {
            this.listData = resp.data.model;
          }
        }).finally(() => {
          loadingComponent.close();
        });
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
              this.axios.delete(`vehicles/${vehicleSelected.id}`).then(resp => {
                if (resp.data.success) {
                  this.$toast.open({
                    message: 'Vehicle deleted successfully',
                    type: 'is-success'
                  });
                  this.$refs.listVehicles.clearSelected();
                  this.updateGrid();
                } else {
                  this.$toast.open({
                    message: response.data.message,
                    type: 'is-danger',
                    position: 'is-bottom',
                  });
                }
              }).catch((err) => {
                this.$toast.open({
                  message: err.response.data.message,
                  type: 'is-danger',
                  position: 'is-bottom',
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
