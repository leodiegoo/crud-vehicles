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
            <crud-button type="is-success" @click="save">Save</crud-button>
          </form>
        </div>
      </div>
    </crud-section-container>
  </section>
</template>


<script>
  export default {
    name: 'VehicleTypeForm',
    data: () => ({
      form: {
        name: '',
        desc: ''
      },
      listVehicleTypes: [],
      titlePage: 'Add new type of vehicle',
      isUpdating: false
    }),
    methods: {
      save() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        let form = this.form;
        if (this.isUpdating) {
          this.axios.put(`/vehicleTypes/${form.id}`, form).then(resp => {
            if (resp.data.success) {
              this.$toast.open({
                message: 'Type updated successfully',
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
          this.axios.post(`/vehicleTypes`, form).then(resp => {
            if (resp.data.success) {
              this.$toast.open({
                message: 'Type created successfully',
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
        this.axios(`/vehicleTypes/${id}`).then(resp => {
          if (resp.data.success) {
            this.updateForm(resp.data.model)
          }
        }).finally(() => {
          loadingComponent.close();
        });
      },
      updateForm(data) {
        this.form = data;
      }
    },
    mounted() {
      const query = this.$route.query;
      if (query.id) {
        this.findById(query.id);
        this.titlePage = "Update type of vehicle";
        this.isUpdating = true;
      }
    }
  }
</script>
