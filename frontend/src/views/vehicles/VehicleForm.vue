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
                         :list-data="vehicles_types" value-option="id" label-option="name"
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
  import {db} from '../../main';

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
      vehicles_types: [],
      titlePage: 'Add new vehicle',
      isUpdating: false
    }),
    firestore() {
      return {
        vehicles_types: db.collection('vehicletype')
      }
    },
    methods: {
      save() {
        const loadingComponent = this.$loading.open({
          container: null
        });
        let form = {...this.form};
        delete form.vehicleType;
        delete form.id;
        if (this.isUpdating) {
          db.collection('vehicle').doc(this.form.id).set(form).then(() => {
            this.$toast.open({
              message: 'Vehicle updated successfully',
              type: 'is-success'
            });
          }).finally(() => {
            loadingComponent.close();
          });
        } else {
          db.collection('vehicle').add(form).then(() => {
            this.$toast.open({
              message: 'Vehicle updated successfully',
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
        db.collection('vehicle').doc(id).get().then(resp => {
          this.updateForm(resp.data());
          this.form.id = id;
        }).finally(() => {
          loadingComponent.close();
        });
      },
      updateForm(data) {
        this.form = data;
      },
    },
    mounted() {
      const query = this.$route.query;
      if (query.id) {
        this.findById(query.id);
        this.titlePage = "Update vehicle";
        this.isUpdating = true;
      }
    }
  }
</script>
