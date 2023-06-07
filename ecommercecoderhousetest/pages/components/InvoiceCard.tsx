import React from 'react';
import styled from 'styled-components';
import useState from 'react';
import { InvoiceDetails } from '../services/fetchData';
import { useEffect } from 'react';

interface Invoice {
	id: number;
	title: string;
	total: string;
	createdAt: string;
}

interface CardProps {
	invoice: Invoice;
}

const Card = styled.div`
	background-color: black;
	color: white;
	padding: 16px;
	border-radius: 8px;
`;

const Title = styled.h2`
	font-size: 24px;
	margin-bottom: 8px;
`;

const Total = styled.p`
	font-size: 18px;
	margin-bottom: 8px;
`;

const CreatedAt = styled.p`
	font-size: 14px;
`;

const [invoiceDetails, setInvoiceDetails] = useState<InvoiceDetails[]>([]);
const [selectedInvoice, setSelectedInvoice] = useState<Invoice | null>(null);
const [isOpen, setIsOpen] = useState(false);

useEffect(() => {
	async function getData() {
		const result = await fetchClients();
		setInvoiceDetails(result);
	}
	getData();
}, []);

const handleModalClick = (invoice: Invoice) => {
	console.log('invoice', invoice);
	setSelectedInvoice(invoice.total);
	setIsOpen(true);
};

const onClose = () => {
	setSelectedInvoice(null);
	setIsOpen(false);
};

const InvoiceCard: React.FC<CardProps> = ({ invoice }) => {
	return (
		<Card className="mt-2 ml-1 mr-5">
			<div className="flex flex-row">
				<div className="flex-1">
					<Title>{invoice.title}</Title>
					<Total>Total: {invoice.total}</Total>
					<CreatedAt>Created at: {invoice.createdAt}</CreatedAt>
				</div>
				<div className="flex-2">
					<button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
						open details
					</button>
				</div>
			</div>
		</Card>
	);
};

export default InvoiceCard;
