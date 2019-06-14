<template>
  <section>
    <crud-section-container>
      <h1 class="title">{{titlePage}}</h1>
    </crud-section-container>
    <crud-section-container>
      <div class="columns">
        <div class="column">
          <form>
            <crud-input label="Name" v-model="form.name"></crud-input>
            <crud-input label="Description" v-model="form.desc"></crud-input>
            <crud-select label="Type" v-model="form.idVehicleType"
                         :list-data="listVehicleTypes" value-option="id" label-option="name"
                         placeholder="Select a Vehicle"
            ></crud-select>
            <crud-input label="Plate" v-model="form.plate"></crud-input>
            <crud-button type="is-success" @click="save">Save</crud-button>
          </form>
        </div>
      </div>
    </crud-section-container>
  </section>
</template>


<script>
  export default {
    name: 'VehicleForm',
    data: () => ({
      form: {
        name: '',
        desc: '',
        idVehicleType: '',
        plate: '',
        vehicleType: {}
      },
      listVehicleTypes: [],
      titlePage: 'Add new vehicle',
      isUpdating: false
    }),
    methods: {
      save() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        let form = this.form;
        delete form.vehicleType;
        if (this.isUpdating) {
          this.axios.put(`/vehicles/${form.id}`, form).then(resp => {
            if (resp.data.success) {
              this.$toast.open({
                message: 'Vehicle updated successfully',
                type: 'is-success'
              });
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
          });
        } else {
          this.axios.post(`/vehicles`, form).then(resp => {
            if (resp.data.success) {
              this.$toast.open({
                message: 'Vehicle created successfully',
                type: 'is-success'
              });
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
          });
        }
      },
      findById(id) {
        const loadingComponent = this.$loading.open({
          container: null
        });
        this.axios(`/vehicles/${id}`).then(resp => {
          if (resp.data.success) {
            this.updateForm(resp.data.model)
          }
        }).finally(() => {
          loadingComponent.close();
        });
      },
      updateForm(data) {
        this.form = data;
      },
      getVehicleTypes() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        this.axios(`/vehicleTypes`).then(resp => {
          if (resp.data.success) {
            this.listVehicleTypes = resp.data.model;
          }
        }).finally(() => {
          loadingComponent.close();
        });
      }
    },
    mounted() {
      const query = this.$route.query;
      if (query.id) {
        this.findById(query.id);
        this.titlePage = "Update vehicle";
        this.isUpdating = true;
      }
      this.getVehicleTypes();
    }
  }
</script>
