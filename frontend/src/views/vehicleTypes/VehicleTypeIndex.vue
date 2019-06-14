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
    <crud-table ref="listVehicleTypes" focusable :list-data="listData" :columns="columns" style="margin-top: 10px;"/>
  </section>
</template>

<script>
  export default {
    name: 'VehicleTypeIndex',
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
        this.$router.push({path: '/vehicleTypes/form'})
      },
      updateGrid() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        this.axios(`/vehicleTypes`).then(resp => {
          if (resp.data.success) {
            this.listData = resp.data.model;
          }
        }).finally(() => {
          loadingComponent.close();
        });
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
              this.axios.delete(`vehicleTypes/${vehicleTypeSelected.id}`).then(resp => {
                if (resp.data.success) {
                  this.$toast.open({
                    message: 'Type deleted successfully',
                    type: 'is-success'
                  });
                  this.$refs.listVehicleTypes.clearSelected();
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
