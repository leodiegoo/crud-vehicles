import CrudTable from './CrudTable'
import CrudButton from './CrudButton'
import CrudSectionContainer from './CrudSectionContainer'
import CrudInput from './CrudInput'
import CrudSelect from './CrudSelect'

export default {
  components: {
    CrudTable,
    CrudButton,
    CrudSectionContainer,
    CrudInput,
    CrudSelect
  },
  install(Vue, options) {
    Vue.component('crud-table', CrudTable);
    Vue.component('crud-button', CrudButton);
    Vue.component('crud-section-container', CrudSectionContainer);
    Vue.component('crud-input', CrudInput);
    Vue.component('crud-select', CrudSelect)
  }
}
