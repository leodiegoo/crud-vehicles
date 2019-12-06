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
  import {db} from '../../main';

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
        let form = {...this.form};
        delete form.id;
        if (this.isUpdating) {
          db.collection('vehicletype').doc(this.form.id).set(form).then(() => {
            this.$toast.open({
              message: 'Type updated successfully',
              type: 'is-success'
            });
          }).finally(() => {
            loadingComponent.close();
          });
        } else {
          db.collection('vehicletype').add(form).then(() => {
            this.$toast.open({
              message: 'Type updated successfully',
              type: 'is-success'
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
        db.collection('vehicletype').doc(id).get().then(resp => {
          this.updateForm(resp.data());
          this.form.id = id;
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
