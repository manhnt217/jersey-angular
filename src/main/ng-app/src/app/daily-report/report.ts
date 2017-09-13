export class Report {
  id: Number
  backlogID: String
  backlogName: String
  taskID: String
  taskName: String
  status: String
  reporter: String
  startedDate: Date
  endDate: Date
  issue: String

  constructor() {

  }

  copy(r) {
    this.id             = r['id']
    this.backlogID      = r['backlogID']
    this.backlogName    = r['backlogName']
    this.taskID         = r['taskID']
    this.taskName       = r['taskName']
    this.status         = r['status']
    this.reporter       = r['reporter']
    this.startedDate    = r['startedDate']
    this.endDate        = r['endDate']
    this.issue          = r['issue']
  }
}
